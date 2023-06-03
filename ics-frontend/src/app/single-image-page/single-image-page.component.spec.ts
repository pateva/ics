import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleImagePageComponent } from './single-image-page.component';

describe('SingleImagePageComponent', () => {
  let component: SingleImagePageComponent;
  let fixture: ComponentFixture<SingleImagePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SingleImagePageComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(SingleImagePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
