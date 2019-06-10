/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MExtraNewsUpdateComponent } from 'app/entities/m-extra-news/m-extra-news-update.component';
import { MExtraNewsService } from 'app/entities/m-extra-news/m-extra-news.service';
import { MExtraNews } from 'app/shared/model/m-extra-news.model';

describe('Component Tests', () => {
  describe('MExtraNews Management Update Component', () => {
    let comp: MExtraNewsUpdateComponent;
    let fixture: ComponentFixture<MExtraNewsUpdateComponent>;
    let service: MExtraNewsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MExtraNewsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MExtraNewsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MExtraNewsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MExtraNewsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MExtraNews(123);
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
        const entity = new MExtraNews();
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
