/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestStageConditionDescriptionDeleteDialogComponent } from 'app/entities/m-quest-stage-condition-description/m-quest-stage-condition-description-delete-dialog.component';
import { MQuestStageConditionDescriptionService } from 'app/entities/m-quest-stage-condition-description/m-quest-stage-condition-description.service';

describe('Component Tests', () => {
  describe('MQuestStageConditionDescription Management Delete Component', () => {
    let comp: MQuestStageConditionDescriptionDeleteDialogComponent;
    let fixture: ComponentFixture<MQuestStageConditionDescriptionDeleteDialogComponent>;
    let service: MQuestStageConditionDescriptionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestStageConditionDescriptionDeleteDialogComponent]
      })
        .overrideTemplate(MQuestStageConditionDescriptionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestStageConditionDescriptionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestStageConditionDescriptionService);
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
