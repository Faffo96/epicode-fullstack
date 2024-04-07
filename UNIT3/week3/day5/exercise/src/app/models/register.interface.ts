import { Movie } from "./movie.interface"

export interface Register {
    firstName: string,
    lastName: string,
    email: string,
    password: string,
    gender: string,
    language: string,
    profileImg: File,
    biography: string,
    username: string,
    favorites: Array<Movie>
}
