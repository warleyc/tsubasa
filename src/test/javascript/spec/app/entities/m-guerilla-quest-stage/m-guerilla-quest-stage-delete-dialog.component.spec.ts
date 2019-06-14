/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGuerillaQuestStageDeleteDialogComponent } from 'app/entities/m-guerilla-quest-stage/m-guerilla-quest-stage-delete-dialog.component';
import { MGuerillaQuestStageService } from 'app/entities/m-guerilla-quest-stage/m-guerilla-quest-stage.service';

describe('Component Tests', () => {
  describe('MGuerillaQuestStage Management Delete Component', () => {
    let comp: MGuerillaQuestStageDeleteDialogComponent;
    let fixture: ComponentFixture<MGuerillaQuestStageDeleteDialogComponent>;
    let service: MGuerillaQuestStageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuerillaQuestStageDeleteDialogComponent]
      })
        .overrideTemplate(MGuerillaQuestStageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuerillaQuestStageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuerillaQuestStageService);
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
