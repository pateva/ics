import { Label } from "./label";

export interface Image {
imageId: number,
imageUrl: number,
width: number,
height: number,
tags: Array<Label>
}