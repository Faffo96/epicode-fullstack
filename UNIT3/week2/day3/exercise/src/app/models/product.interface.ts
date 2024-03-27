export interface Product {
    id: number;
    title: string;
    description: string;
    price: number;
    brand: string;
    thumbnail: string;
    isLiked?: boolean;
    quantity: number;
}
