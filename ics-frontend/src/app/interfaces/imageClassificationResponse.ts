import { Label } from "./labelResponse";

export interface ImageClassificationResponse {
    imageId: number,
    imageUrl: string,
    createdAt: string,
    updatedAt: string, 
    width: number,
    height: number,
    imageService: string,
    labels: Label[] 
}