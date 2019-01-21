import { IArtwork } from 'app/shared/model//artwork.model';

export interface IGallery {
    id?: string;
    title?: string;
    artworks?: IArtwork[];
}

export class Gallery implements IGallery {
    constructor(public id?: string, public title?: string, public artworks?: IArtwork[]) {}
}
