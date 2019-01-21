import { Moment } from 'moment';
import { IGallery } from 'app/shared/model//gallery.model';

export interface IArtist {
    id?: string;
    firstName?: string;
    lastName?: string;
    birthDate?: Moment;
    city?: string;
    country?: string;
    biography?: string;
    cv?: string;
    galleries?: IGallery[];
}

export class Artist implements IArtist {
    constructor(
        public id?: string,
        public firstName?: string,
        public lastName?: string,
        public birthDate?: Moment,
        public city?: string,
        public country?: string,
        public biography?: string,
        public cv?: string,
        public galleries?: IGallery[]
    ) {}
}
