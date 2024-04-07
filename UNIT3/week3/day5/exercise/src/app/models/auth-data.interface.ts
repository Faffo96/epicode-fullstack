import { Movie } from "./movie.interface"

export interface AuthData {
    accessToken: string,
    user: {
        id: number,
        firstName: string,
        lastName: string,
        email: string,
        password: string,
        gender: string,
        language: string,
        profileImg: File,
        biography: string,
        username: string,
        favorites: Movie[]
    }
}
