import { TestBed } from '@angular/core/testing';

import { ServerSentService } from './server-sent.service';

describe('ServerSentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServerSentService = TestBed.get(ServerSentService);
    expect(service).toBeTruthy();
  });
});
