/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MStampUpdateComponent } from 'app/entities/m-stamp/m-stamp-update.component';
import { MStampService } from 'app/entities/m-stamp/m-stamp.service';
import { MStamp } from 'app/shared/model/m-stamp.model';

describe('Component Tests', () => {
  describe('MStamp Management Update Component', () => {
    let comp: MStampUpdateComponent;
    let fixture: ComponentFixture<MStampUpdateComponent>;
    let service: MStampService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MStampUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MStampUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MStampUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MStampService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MStamp(123);
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
        const entity = new MStamp();
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
