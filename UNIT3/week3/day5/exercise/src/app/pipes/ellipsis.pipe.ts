import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'ellipsis',
})
export class EllipsisPipe implements PipeTransform {
    transform(value: string): string {
        if (value.length <= 25) {
            return value;
        } else {
            return value.slice(0, 25) + '...';
        }
    }
}

