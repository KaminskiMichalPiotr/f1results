import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RaceResultEditorModalComponent} from './race-result-editor-modal.component';

describe('RaceResultEditorModalComponent', () => {
  let component: RaceResultEditorModalComponent;
  let fixture: ComponentFixture<RaceResultEditorModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RaceResultEditorModalComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RaceResultEditorModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
