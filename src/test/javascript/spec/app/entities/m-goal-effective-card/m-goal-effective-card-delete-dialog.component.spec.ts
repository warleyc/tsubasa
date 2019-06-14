/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGoalEffectiveCardDeleteDialogComponent } from 'app/entities/m-goal-effective-card/m-goal-effective-card-delete-dialog.component';
import { MGoalEffectiveCardService } from 'app/entities/m-goal-effective-card/m-goal-effective-card.service';

describe('Component Tests', () => {
  describe('MGoalEffectiveCard Management Delete Component', () => {
    let comp: MGoalEffectiveCardDeleteDialogComponent;
    let fixture: ComponentFixture<MGoalEffectiveCardDeleteDialogComponent>;
    let service: MGoalEffectiveCardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGoalEffectiveCardDeleteDialogComponent]
      })
        .overrideTemplate(MGoalEffectiveCardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGoalEffectiveCardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGoalEffectiveCardService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
