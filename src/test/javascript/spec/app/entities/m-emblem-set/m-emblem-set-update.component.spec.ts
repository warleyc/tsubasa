/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEmblemSetUpdateComponent } from 'app/entities/m-emblem-set/m-emblem-set-update.component';
import { MEmblemSetService } from 'app/entities/m-emblem-set/m-emblem-set.service';
import { MEmblemSet } from 'app/shared/model/m-emblem-set.model';

describe('Component Tests', () => {
  describe('MEmblemSet Management Update Component', () => {
    let comp: MEmblemSetUpdateComponent;
    let fixture: ComponentFixture<MEmblemSetUpdateComponent>;
    let service: MEmblemSetService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEmblemSetUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MEmblemSetUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MEmblemSetUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MEmblemSetService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MEmblemSet(123);
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
        const entity = new MEmblemSet();
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
