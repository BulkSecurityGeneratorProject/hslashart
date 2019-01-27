import { Moment } from 'moment';
import { IGallery } from 'app/shared/model//gallery.model';
import { IArtist } from 'app/shared/model//artist.model';

export const enum Currency {
    CA = 'CA',
    US = 'US'
}

export const enum Availability {
    AVAILABLE = 'AVAILABLE',
    SEE_ONLY = 'SEE_ONLY',
    SOLD = 'SOLD'
}

export interface IArtwork {
    id?: string;
    title?: string;
    description?: string;
    price?: number;
    currency?: Currency;
    image?: string;
    thumbnail?: string;
    dimensions?: string;
    creationDate?: Moment;
    creditLine?: string;
    copyrightImage?: string;
    classification?: string;
    availability?: Availability;
    galleries?: IGallery[];
    artist?: IArtist;
}

export class Artwork implements IArtwork {
    constructor(
        public id?: string,
        public title?: string,
        public description?: string,
        public price?: number,
        public currency?: Currency,
        public image?: string,
        public thumbnail?: string,
        public dimensions?: string,
        public creationDate?: Moment,
        public creditLine?: string,
        public copyrightImage?: string,
        public classification?: string,
        public availability?: Availability,
        public galleries?: IGallery[],
        public artist?: IArtist
    ) {}
}
