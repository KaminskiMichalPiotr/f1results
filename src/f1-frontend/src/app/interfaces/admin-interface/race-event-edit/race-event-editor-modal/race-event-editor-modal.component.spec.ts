import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RaceEventEditorModalComponent} from './race-event-editor-modal.component';

describe('RaceEventEditorModalComponent', () => {
  let component: RaceEventEditorModalComponent;
  let fixture: ComponentFixture<RaceEventEditorModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RaceEventEditorModalComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RaceEventEditorModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
