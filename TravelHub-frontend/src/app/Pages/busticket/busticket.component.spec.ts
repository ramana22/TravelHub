import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusticketComponent } from './busticket.component';

describe('BusticketComponent', () => {
  let component: BusticketComponent;
  let fixture: ComponentFixture<BusticketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BusticketComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BusticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
