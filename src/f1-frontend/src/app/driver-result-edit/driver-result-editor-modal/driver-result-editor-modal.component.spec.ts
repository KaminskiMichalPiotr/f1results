import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DriverResultEditorModalComponent} from './driver-result-editor-modal.component';

describe('DriverResultEditorModalComponent', () => {
  let component: DriverResultEditorModalComponent;
  let fixture: ComponentFixture<DriverResultEditorModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DriverResultEditorModalComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DriverResultEditorModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
