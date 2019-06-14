/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonQuestStageDeleteDialogComponent } from 'app/entities/m-marathon-quest-stage/m-marathon-quest-stage-delete-dialog.component';
import { MMarathonQuestStageService } from 'app/entities/m-marathon-quest-stage/m-marathon-quest-stage.service';

describe('Component Tests', () => {
  describe('MMarathonQuestStage Management Delete Component', () => {
    let comp: MMarathonQuestStageDeleteDialogComponent;
    let fixture: ComponentFixture<MMarathonQuestStageDeleteDialogComponent>;
    let service: MMarathonQuestStageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonQuestStageDeleteDialogComponent]
      })
        .overrideTemplate(MMarathonQuestStageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonQuestStageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonQuestStageService);
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
