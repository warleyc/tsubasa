/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MInitUserDeckDeleteDialogComponent } from 'app/entities/m-init-user-deck/m-init-user-deck-delete-dialog.component';
import { MInitUserDeckService } from 'app/entities/m-init-user-deck/m-init-user-deck.service';

describe('Component Tests', () => {
  describe('MInitUserDeck Management Delete Component', () => {
    let comp: MInitUserDeckDeleteDialogComponent;
    let fixture: ComponentFixture<MInitUserDeckDeleteDialogComponent>;
    let service: MInitUserDeckService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MInitUserDeckDeleteDialogComponent]
      })
        .overrideTemplate(MInitUserDeckDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MInitUserDeckDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MInitUserDeckService);
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
