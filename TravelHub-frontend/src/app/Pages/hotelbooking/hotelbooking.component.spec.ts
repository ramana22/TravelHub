import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelbookingComponent } from './hotelbooking.component';

describe('HotelbookingComponent', () => {
  let component: HotelbookingComponent;
  let fixture: ComponentFixture<HotelbookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HotelbookingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HotelbookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
