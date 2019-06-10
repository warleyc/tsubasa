/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCharacterBookDeleteDialogComponent } from 'app/entities/m-character-book/m-character-book-delete-dialog.component';
import { MCharacterBookService } from 'app/entities/m-character-book/m-character-book.service';

describe('Component Tests', () => {
  describe('MCharacterBook Management Delete Component', () => {
    let comp: MCharacterBookDeleteDialogComponent;
    let fixture: ComponentFixture<MCharacterBookDeleteDialogComponent>;
    let service: MCharacterBookService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCharacterBookDeleteDialogComponent]
      })
        .overrideTemplate(MCharacterBookDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCharacterBookDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCharacterBookService);
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
