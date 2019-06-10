/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MAdventQuestWorldDeleteDialogComponent } from 'app/entities/m-advent-quest-world/m-advent-quest-world-delete-dialog.component';
import { MAdventQuestWorldService } from 'app/entities/m-advent-quest-world/m-advent-quest-world.service';

describe('Component Tests', () => {
  describe('MAdventQuestWorld Management Delete Component', () => {
    let comp: MAdventQuestWorldDeleteDialogComponent;
    let fixture: ComponentFixture<MAdventQuestWorldDeleteDialogComponent>;
    let service: MAdventQuestWorldService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAdventQuestWorldDeleteDialogComponent]
      })
        .overrideTemplate(MAdventQuestWorldDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MAdventQuestWorldDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MAdventQuestWorldService);
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
