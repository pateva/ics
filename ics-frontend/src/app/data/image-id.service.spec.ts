import { TestBed } from '@angular/core/testing';

import { ImageIdService } from './image-id.service';

describe('ImageIdService', () => {
  let service: ImageIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImageIdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
