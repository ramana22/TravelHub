import { TestBed } from '@angular/core/testing';

import { TravelHubServiceService } from './travel-hub-service.service';

describe('TravelHubServiceService', () => {
  let service: TravelHubServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TravelHubServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
