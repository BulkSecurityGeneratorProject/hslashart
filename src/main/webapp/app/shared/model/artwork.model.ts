import { IGallery } from 'app/shared/model/gallery.model';

export interface IArtwork {
    id?: string;
    title?: string;
    galleries?: IGallery[];
}

export class Artwork implements IArtwork {
    constructor(public id?: string, public title?: string, public galleries?: IGallery[]) {}
}
