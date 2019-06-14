/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSituationAnnounceUpdateComponent } from 'app/entities/m-situation-announce/m-situation-announce-update.component';
import { MSituationAnnounceService } from 'app/entities/m-situation-announce/m-situation-announce.service';
import { MSituationAnnounce } from 'app/shared/model/m-situation-announce.model';

describe('Component Tests', () => {
  describe('MSituationAnnounce Management Update Component', () => {
    let comp: MSituationAnnounceUpdateComponent;
    let fixture: ComponentFixture<MSituationAnnounceUpdateComponent>;
    let service: MSituationAnnounceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSituationAnnounceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MSituationAnnounceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MSituationAnnounceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MSituationAnnounceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MSituationAnnounce(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MSituationAnnounce();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
