import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainticketComponent } from './trainticket.component';

describe('TrainticketComponent', () => {
  let component: TrainticketComponent;
  let fixture: ComponentFixture<TrainticketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrainticketComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TrainticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
