import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SeasonEditorModalComponent} from './season-editor-modal.component';

describe('SeasonEditorModalComponent', () => {
  let component: SeasonEditorModalComponent;
  let fixture: ComponentFixture<SeasonEditorModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SeasonEditorModalComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeasonEditorModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
