/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MExtraNewsDetailComponent } from 'app/entities/m-extra-news/m-extra-news-detail.component';
import { MExtraNews } from 'app/shared/model/m-extra-news.model';

describe('Component Tests', () => {
  describe('MExtraNews Management Detail Component', () => {
    let comp: MExtraNewsDetailComponent;
    let fixture: ComponentFixture<MExtraNewsDetailComponent>;
    const route = ({ data: of({ mExtraNews: new MExtraNews(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MExtraNewsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MExtraNewsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MExtraNewsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mExtraNews).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
