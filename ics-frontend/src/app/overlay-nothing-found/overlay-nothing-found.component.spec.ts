import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OverlayNothingFoundComponent } from './overlay-nothing-found.component';

describe('OverlayNothingFoundComponent', () => {
  let component: OverlayNothingFoundComponent;
  let fixture: ComponentFixture<OverlayNothingFoundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OverlayNothingFoundComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OverlayNothingFoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
