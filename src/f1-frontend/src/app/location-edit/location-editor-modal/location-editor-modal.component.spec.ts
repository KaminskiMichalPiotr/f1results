import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LocationEditorModalComponent} from './location-editor-modal.component';

describe('LocationEditorModalComponent', () => {
  let component: LocationEditorModalComponent;
  let fixture: ComponentFixture<LocationEditorModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LocationEditorModalComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LocationEditorModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
