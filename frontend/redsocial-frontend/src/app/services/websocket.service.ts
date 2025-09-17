import { Injectable } from '@angular/core';
import { Client } from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs';

export interface LikeUpdateMessage {
  postId: number;
  likeCount: number;
  userHasLiked: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private stompClient: Client | null = null;
  private likeUpdatesSubject = new BehaviorSubject<LikeUpdateMessage | null>(null);
  public likeUpdates$ = this.likeUpdatesSubject.asObservable();

  constructor() {
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection(): void {
    this.stompClient = new Client({
      brokerURL: 'ws://localhost:8080/ws',
      debug: (str) => {
        console.log('STOMP Debug:', str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    this.stompClient.onConnect = (frame) => {
      console.log('Connected: ' + frame);
      // No necesitamos suscribirnos a todas las publicaciones aquí
      // Las suscripciones específicas se manejarán en los componentes
    };

    this.stompClient.onStompError = (frame) => {
      console.error('Broker reported error: ' + frame.headers['message']);
      console.error('Additional details: ' + frame.body);
    };

    this.stompClient.activate();
  }

  subscribeToPostLikes(postId: number): void {
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.subscribe(`/topic/likes/${postId}`, (message) => {
        const likeUpdate: LikeUpdateMessage = JSON.parse(message.body);
        this.likeUpdatesSubject.next(likeUpdate);
      });
    }
  }

  disconnect(): void {
    if (this.stompClient !== null) {
      this.stompClient.deactivate();
    }
    console.log('Disconnected');
  }
}