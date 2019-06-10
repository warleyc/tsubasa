/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryZhDeleteDialogComponent } from 'app/entities/m-dictionary-zh/m-dictionary-zh-delete-dialog.component';
import { MDictionaryZhService } from 'app/entities/m-dictionary-zh/m-dictionary-zh.service';

describe('Component Tests', () => {
  describe('MDictionaryZh Management Delete Component', () => {
    let comp: MDictionaryZhDeleteDialogComponent;
    let fixture: ComponentFixture<MDictionaryZhDeleteDialogComponent>;
    let service: MDictionaryZhService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryZhDeleteDialogComponent]
      })
        .overrideTemplate(MDictionaryZhDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryZhDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryZhService);
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
