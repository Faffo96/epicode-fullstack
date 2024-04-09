import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'ellipsis',
})
export class EllipsisPipe implements PipeTransform {
    transform(value: string): string {
        if (value.length <= 18) {
            return value;
        } else {
            return value.slice(0, 18) + '...';
        }
    }
}

