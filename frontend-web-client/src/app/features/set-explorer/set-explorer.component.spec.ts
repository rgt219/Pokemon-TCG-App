import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SetExplorerComponent } from './set-explorer.component';

describe('SetExplorerComponent', () => {
  let component: SetExplorerComponent;
  let fixture: ComponentFixture<SetExplorerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SetExplorerComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SetExplorerComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
