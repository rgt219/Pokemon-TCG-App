import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SetDetailComponent } from './set-detail.component';

describe('SetDetailComponent', () => {
  let component: SetDetailComponent;
  let fixture: ComponentFixture<SetDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SetDetailComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SetDetailComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
