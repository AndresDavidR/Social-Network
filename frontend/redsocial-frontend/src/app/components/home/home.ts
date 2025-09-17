import { Component, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreatePost } from '../create-post/create-post';
import { PostList } from '../post-list/post-list';

@Component({
  selector: 'app-home',
  imports: [CommonModule, CreatePost, PostList],
  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class HomeComponent {
  @ViewChild(PostList) postList!: PostList;

  onPostCreated(): void {
    // Cuando se crea un post, recargar la lista
    if (this.postList) {
      this.postList.loadPosts();
    }
  }
}
