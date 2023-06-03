import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageClassificationComponent } from './image-classification.component';

describe('ImageClassificationComponent', () => {
  let component: ImageClassificationComponent;
  let fixture: ComponentFixture<ImageClassificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ImageClassificationComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ImageClassificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
