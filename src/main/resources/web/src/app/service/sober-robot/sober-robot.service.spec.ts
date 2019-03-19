import { TestBed } from '@angular/core/testing';

import { SoberRobotService } from './sober-robot.service';

describe('SoberRobotService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SoberRobotService = TestBed.get(SoberRobotService);
    expect(service).toBeTruthy();
  });
});
