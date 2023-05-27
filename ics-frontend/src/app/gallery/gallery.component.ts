import { Component } from '@angular/core';
// import { MatToolbarModule } from '@angular/material/toolbar';
// import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'ics-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent {
  value: string = ''; // Declare the 'value' property
  imageUrls: string[] = [
    'https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/697BB0C4E76D527F55B6A7D6C4C719236D06A18FD7396F7514342BF4E301AAED/scale?width=1200&aspectRatio=1.78&format=jpeg',
    'https://www.indiewire.com/wp-content/uploads/2022/06/Lilo-Stitch.jpg',
    'https://www.indiewire.com/wp-content/uploads/2022/06/Lilo-Stitch.jpg',
    'https://www.indiewire.com/wp-content/uploads/2022/06/Lilo-Stitch.jpg',
    'https://www.indiewire.com/wp-content/uploads/2022/06/Lilo-Stitch.jpg',
    'https://www.indiewire.com/wp-content/uploads/2022/06/Lilo-Stitch.jpg',
    'https://pyxis.nymag.com/v1/imgs/e95/5d4/e02729391d31693ad53b90ab5a9644c68c-lilo-stitch-6.1x.rsquare.w1400.jpg',
    'https://pyxis.nymag.com/v1/imgs/e95/5d4/e02729391d31693ad53b90ab5a9644c68c-lilo-stitch-6.1x.rsquare.w1400.jpg',
    'https://pyxis.nymag.com/v1/imgs/e95/5d4/e02729391d31693ad53b90ab5a9644c68c-lilo-stitch-6.1x.rsquare.w1400.jpg',
    'https://pyxis.nymag.com/v1/imgs/e95/5d4/e02729391d31693ad53b90ab5a9644c68c-lilo-stitch-6.1x.rsquare.w1400.jpg',
    'https://www.indiewire.com/wp-content/uploads/2022/06/Lilo-Stitch.jpg',
    'https://www.indiewire.com/wp-content/uploads/2022/06/Lilo-Stitch.jpg'
  ];
}
