import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DriverResultEditComponent} from './driver-result-edit.component';

describe('DriverResultEditComponent', () => {
  let component: DriverResultEditComponent;
  let fixture: ComponentFixture<DriverResultEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DriverResultEditComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DriverResultEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
