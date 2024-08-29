export interface AuthData {
  user: {
    email: string;
    name: string;
    id: number;
  };
  accessToken: string;
}
