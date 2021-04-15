import { User } from "./user";

export interface Post{
    postId: number,
    description: string,
    photos: File[],
    userId: User,
    users: [];
}