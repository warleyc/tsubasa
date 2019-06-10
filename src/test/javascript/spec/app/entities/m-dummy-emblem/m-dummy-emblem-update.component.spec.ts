/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDummyEmblemUpdateComponent } from 'app/entities/m-dummy-emblem/m-dummy-emblem-update.component';
import { MDummyEmblemService } from 'app/entities/m-dummy-emblem/m-dummy-emblem.service';
import { MDummyEmblem } from 'app/shared/model/m-dummy-emblem.model';

describe('Component Tests', () => {
  describe('MDummyEmblem Management Update Component', () => {
    let comp: MDummyEmblemUpdateComponent;
    let fixture: ComponentFixture<MDummyEmblemUpdateComponent>;
    let service: MDummyEmblemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDummyEmblemUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDummyEmblemUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDummyEmblemUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDummyEmblemService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDummyEmblem(123);
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
        const entity = new MDummyEmblem();
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
