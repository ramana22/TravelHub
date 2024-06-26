import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeprofileComponent } from './changeprofile.component';

describe('ChangeprofileComponent', () => {
  let component: ChangeprofileComponent;
  let fixture: ComponentFixture<ChangeprofileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChangeprofileComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChangeprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
