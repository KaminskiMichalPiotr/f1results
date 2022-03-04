import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RaceResultEditComponent} from './race-result-edit.component';

describe('RaceResultEditComponent', () => {
  let component: RaceResultEditComponent;
  let fixture: ComponentFixture<RaceResultEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RaceResultEditComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RaceResultEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
