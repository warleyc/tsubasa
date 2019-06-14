/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MPlayableCardDeleteDialogComponent } from 'app/entities/m-playable-card/m-playable-card-delete-dialog.component';
import { MPlayableCardService } from 'app/entities/m-playable-card/m-playable-card.service';

describe('Component Tests', () => {
  describe('MPlayableCard Management Delete Component', () => {
    let comp: MPlayableCardDeleteDialogComponent;
    let fixture: ComponentFixture<MPlayableCardDeleteDialogComponent>;
    let service: MPlayableCardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPlayableCardDeleteDialogComponent]
      })
        .overrideTemplate(MPlayableCardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPlayableCardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPlayableCardService);
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
