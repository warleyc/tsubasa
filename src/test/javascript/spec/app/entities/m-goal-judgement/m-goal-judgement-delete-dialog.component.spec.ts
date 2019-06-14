/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGoalJudgementDeleteDialogComponent } from 'app/entities/m-goal-judgement/m-goal-judgement-delete-dialog.component';
import { MGoalJudgementService } from 'app/entities/m-goal-judgement/m-goal-judgement.service';

describe('Component Tests', () => {
  describe('MGoalJudgement Management Delete Component', () => {
    let comp: MGoalJudgementDeleteDialogComponent;
    let fixture: ComponentFixture<MGoalJudgementDeleteDialogComponent>;
    let service: MGoalJudgementService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGoalJudgementDeleteDialogComponent]
      })
        .overrideTemplate(MGoalJudgementDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGoalJudgementDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGoalJudgementService);
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
