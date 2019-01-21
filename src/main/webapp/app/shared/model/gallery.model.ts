import { Moment } from 'moment';
import { IArtwork } from 'app/shared/model//artwork.model';
import { IArtist } from 'app/shared/model//artist.model';

export interface IGallery {
    id?: string;
    title?: string;
    creationDate?: Moment;
    description?: string;
    order?: number;
    artworks?: IArtwork[];
    artist?: IArtist;
}

export class Gallery implements IGallery {
    constructor(
        public id?: string,
        public title?: string,
        public creationDate?: Moment,
        public description?: string,
        public order?: number,
        public artworks?: IArtwork[],
        public artist?: IArtist
    ) {}
}
