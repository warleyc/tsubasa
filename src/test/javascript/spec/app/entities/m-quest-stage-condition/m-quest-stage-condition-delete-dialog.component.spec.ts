/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestStageConditionDeleteDialogComponent } from 'app/entities/m-quest-stage-condition/m-quest-stage-condition-delete-dialog.component';
import { MQuestStageConditionService } from 'app/entities/m-quest-stage-condition/m-quest-stage-condition.service';

describe('Component Tests', () => {
  describe('MQuestStageCondition Management Delete Component', () => {
    let comp: MQuestStageConditionDeleteDialogComponent;
    let fixture: ComponentFixture<MQuestStageConditionDeleteDialogComponent>;
    let service: MQuestStageConditionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestStageConditionDeleteDialogComponent]
      })
        .overrideTemplate(MQuestStageConditionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestStageConditionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestStageConditionService);
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
