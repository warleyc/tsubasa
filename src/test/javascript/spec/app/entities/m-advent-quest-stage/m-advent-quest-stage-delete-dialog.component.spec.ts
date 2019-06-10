/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MAdventQuestStageDeleteDialogComponent } from 'app/entities/m-advent-quest-stage/m-advent-quest-stage-delete-dialog.component';
import { MAdventQuestStageService } from 'app/entities/m-advent-quest-stage/m-advent-quest-stage.service';

describe('Component Tests', () => {
  describe('MAdventQuestStage Management Delete Component', () => {
    let comp: MAdventQuestStageDeleteDialogComponent;
    let fixture: ComponentFixture<MAdventQuestStageDeleteDialogComponent>;
    let service: MAdventQuestStageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAdventQuestStageDeleteDialogComponent]
      })
        .overrideTemplate(MAdventQuestStageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MAdventQuestStageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MAdventQuestStageService);
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
