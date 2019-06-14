/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetPlayableCardGroupDeleteDialogComponent } from 'app/entities/m-target-playable-card-group/m-target-playable-card-group-delete-dialog.component';
import { MTargetPlayableCardGroupService } from 'app/entities/m-target-playable-card-group/m-target-playable-card-group.service';

describe('Component Tests', () => {
  describe('MTargetPlayableCardGroup Management Delete Component', () => {
    let comp: MTargetPlayableCardGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MTargetPlayableCardGroupDeleteDialogComponent>;
    let service: MTargetPlayableCardGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetPlayableCardGroupDeleteDialogComponent]
      })
        .overrideTemplate(MTargetPlayableCardGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetPlayableCardGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetPlayableCardGroupService);
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
